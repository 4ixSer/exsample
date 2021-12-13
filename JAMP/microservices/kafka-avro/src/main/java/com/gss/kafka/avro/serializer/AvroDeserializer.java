package com.gss.kafka.avro.serializer;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer {

    protected final Class<T> targetType;

    public AvroDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        T returnObject = null;
        if (bytes != null) {
            try {
                returnObject = populate(bytes);
                log.info("Deserialized data='{}'", returnObject.toString());
            } catch (Exception e) {
                log.error("Unable to Deserialize bytes[] {}", e.getMessage());
            }
        }
        return returnObject;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map configs, boolean isKey) {
    }

    private T populate(byte[] bytes) throws InstantiationException, IllegalAccessException, IOException {
        DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(targetType.newInstance().getSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        return (T) datumReader.read(null, decoder);
    }
}
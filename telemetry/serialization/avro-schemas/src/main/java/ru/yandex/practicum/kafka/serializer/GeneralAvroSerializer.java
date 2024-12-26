package ru.yandex.practicum.kafka.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

/**
 * A general-purpose serializer for serializing objects of classes generated
 * based on Avro schemas into binary data.
 */
public class GeneralAvroSerializer implements Serializer<SpecificRecordBase> {

  private final EncoderFactory encoderFactory = EncoderFactory.get();
  private BinaryEncoder encoder;

  @Override
  public byte[] serialize(String topic, SpecificRecordBase data) {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      byte[] result = null;
      encoder = encoderFactory.binaryEncoder(out,encoder);
      if (data != null) {
        DatumWriter<SpecificRecordBase> writer = new SpecificDatumWriter<>(data.getSchema());
        writer.write(data,encoder);
        encoder.flush();
        result = out.toByteArray();
      }
      return result;
    } catch (IOException e) {
      throw new SerializationException("Error occurs during data serialization for the topic [" + topic + "].", e);
    }
  }
}

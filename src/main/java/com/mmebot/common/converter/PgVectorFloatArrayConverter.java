package com.mmebot.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// DB에는 [0.1, -0.23, ...] 형태 문자열로 나가고, 읽을 때 float[]로 파싱
@Converter(autoApply = false)
public class PgVectorFloatArrayConverter implements AttributeConverter<float[], String> {

    @Override
    public String convertToDatabaseColumn(float[] attribute) {
        if (attribute == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < attribute.length; i++) {
            if (i > 0) sb.append(',');
            // PostgreSQL vector는 공백 없이 "[v1,v2,...]" 형태를 잘 처리함
            sb.append(Float.toString(attribute[i]));
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public float[] convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        String s = dbData.trim();
        if (s.length() < 2 || s.charAt(0) != '[' || s.charAt(s.length()-1) != ']') {
            throw new IllegalArgumentException("Invalid pgvector literal: " + dbData);
        }
        String inner = s.substring(1, s.length()-1).trim();
        if (inner.isEmpty()) return new float[0];

        String[] parts = inner.split("\\s*,\\s*");
        float[] out = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            out[i] = Float.parseFloat(parts[i]);
        }
        return out;
    }
}

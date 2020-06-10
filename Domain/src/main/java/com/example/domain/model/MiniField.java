package com.example.domain.model;

import java.util.Objects;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class MiniField {
    private String miniFieldId;
    private String fieldId;

    public String getMiniFieldId() {
        return miniFieldId;
    }

    public void setMiniFieldId(String miniFieldId) {
        this.miniFieldId = miniFieldId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiniField)) return false;
        MiniField miniField = (MiniField) o;
        return Objects.equals(miniFieldId, miniField.miniFieldId) &&
                Objects.equals(fieldId, miniField.fieldId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miniFieldId, fieldId);
    }
}

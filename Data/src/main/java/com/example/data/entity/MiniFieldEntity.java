package com.example.data.entity;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class MiniFieldEntity {
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
    public String toString() {
        return "MiniFieldEntity{" +
                "miniFieldId='" + miniFieldId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                '}';
    }
}

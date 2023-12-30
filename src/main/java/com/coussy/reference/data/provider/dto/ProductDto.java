package com.coussy.reference.data.provider.dto;

public class ProductDto {

    String id;

    String name;

    public ProductDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // for jackson unmarshall
    public ProductDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

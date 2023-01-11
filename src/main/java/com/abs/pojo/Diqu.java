package com.abs.pojo;


public class Diqu {
    private Integer id;
    private String name;
    private String img;
    private String introduction;

    public Diqu() {
    }

    public Diqu(Integer id, String name, String img, String introduction) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.introduction = introduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Diqu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}

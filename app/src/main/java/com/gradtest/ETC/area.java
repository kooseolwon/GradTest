package com.gradtest.ETC;

/**
 * Created by SM-PC on 2018-09-04.
 */

public enum area {
    ALL(0,"전국"),
    JONGRO(1,"종로구"),
    JUNGGOO(2,"중구"),
    YONGSAN(3,"용산구"),
    SUNGDONG(4,"성동구"),
    GWANGJIN(5,"광진구"),
    DONGDAEMUN(6,"동대문구"),
    JUNGRANG(7,"중랑구"),
    SUNGBOOK(8,"성북구"),
    KANGBOOK(9,"강북구"),
    DOBONG(10,"도봉구"),
    NOWON(11,"노원구"),
    EUNPUNG(12,"은평구"),
    SEODAEMUN(13,"서대문구"),
    MAPO(14,"마포구"),
    YANGCHUN(15,"양천구"),
    KANGSEO(16,"강서구"),
    GURO(17,"구로구"),
    GEUMCHUN(18,"금천구"),
    YOUNGDEUNGPO(19,"영등포구"),
    DONGJACK(20,"동작구"),
    GUANWACK(21,"관악구"),
    SEOCHO(22,"서초구"),
    KANGNAM(23,"강남구"),
    SONGPA(24,"송파구"),
    KANGDONG(25,"강동구");

    public int code;
    public String areaName;

    area(int code, String areaName){
        this.code = code;
        this.areaName = areaName;

    }

    public int getCode(){
        return this.code;
    }
    public String getAreaName(){
        return this.areaName;
    }




}

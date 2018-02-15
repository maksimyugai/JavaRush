package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop {
    public Goods goods;

    @XmlAttribute(name = "count")
    public int count;

    @XmlAttribute(name = "profit")
    public double profit;

    @XmlElement(name = "secretData")
    public String[] secretData;

    public static class Goods {
        @XmlElementWrapper(name = "goods", nillable = true)
        @XmlElement(name = "names")
        List<String> names = new ArrayList<>();
    }
}

package com.example.hitc;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Product> getFakeProducts() {
        List<Product> list = new ArrayList<>();
        
        Product p1 = new Product(1, "MacBook Pro M3", "Chip M3 Pro, 16GB RAM, 512GB SSD", 45000000, "Laptop", R.drawable.laptop);
        p1.setImageUrl("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?q=80&w=500");
        
        Product p2 = new Product(2, "iPhone 15 Pro Max", "Titan tự nhiên, 256GB", 31500000, "Điện thoại", R.drawable.prm);
        p2.setImageUrl("https://images.unsplash.com/photo-1695048133142-1a20484d256e?q=80&w=500");
        
        Product p3 = new Product(3, "Bàn phím cơ AKKO", "Gasket Mount, RGB, Cream Yellow Switch", 1850000, "Linh kiện", R.drawable.banphim);
        p3.setImageUrl("https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?q=80&w=500");
        
        Product p4 = new Product(4, "Chuột Logitech G502", "Hero Sensor 25K, LIGHTSYNC", 1100000, "Linh kiện", R.drawable.chuot);
        p4.setImageUrl("https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?q=80&w=500");
        
        Product p5 = new Product(5, "Tai nghe Sony WH-1000XM5", "Chống ồn chủ động đỉnh cao", 6500000, "Phụ kiện", R.drawable.chongon);
        p5.setImageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=500");
        
        Product p6 = new Product(6, "Màn hình Dell UltraSharp", "4K IPS, 100% sRGB", 12500000, "Linh kiện", R.drawable.manhinhk);
        p6.setImageUrl("https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?q=80&w=500");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        
        return list;
    }
}
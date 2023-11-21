package com.sample.cricket.serviceImpl;

import com.sample.cricket.service.CricketService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Service
public class CricketServiceImpl implements CricketService {
    @Override
    public int getScore() {
        List<String> slist = Arrays.asList("0","1","2","3","4","5","6","w");
        Random rand = new Random();
        int count =0;
        int value =0;
        for(int i=0;i<20;i++) {
            String str = slist.get(rand.nextInt(slist.size()));
            value = str == "w" ? 0: Integer.valueOf(str);
            count+= value;
        }
        return count;
    }
}

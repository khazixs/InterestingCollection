package com.ic.learn.service.impl;

import com.ic.annotation.Output;
import com.ic.learn.service.StudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Output("能行了么？")
    @Override
    public String getName() {
        System.out.println("我叫小红");
        return "hong";
    }
}

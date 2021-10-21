package com.example.supermarket.service;

import com.example.supermarket.dto.PointDto;
import com.example.supermarket.dto.TransactionDto;
import com.example.supermarket.entity.TransactionEntity;

import java.util.List;

public interface PointService {

    PointDto addPoint(TransactionEntity transaction);
    PointDto revertPoint(TransactionEntity transaction);
    PointDto showPoint(int id);
    List<PointDto> showAllPoint();
}

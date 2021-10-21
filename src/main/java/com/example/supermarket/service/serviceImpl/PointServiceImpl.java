package com.example.supermarket.service.serviceImpl;

import com.example.supermarket.dto.PointDto;
import com.example.supermarket.entity.PointEntity;
import com.example.supermarket.entity.TransactionEntity;
import com.example.supermarket.mapper.PointMapper;
import com.example.supermarket.repository.PointRepository;
import com.example.supermarket.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointRepository pointRepository;

    @Override
    public PointDto addPoint(TransactionEntity transaction) {
        PointEntity point =new PointEntity();
        point.setCustomerPhone(transaction.getCustomerPhone());
        point.setTransactionId(transaction.getId());
        if(transaction.getTotal()>=1000)
            point.setPoints(45);
        else
            point.setPoints(0);
        point.setStatus("SUCCESS");
        point = pointRepository.save(point);
        return PointMapper.INSTANCE.toPointDto(point);
    }

    @Override
    public PointDto revertPoint(TransactionEntity transaction) {
        PointEntity point = new PointEntity();
        point = pointRepository.getEntityToUpdate(transaction.getId());
        point.setStatus("REVERTED");
        return PointMapper.INSTANCE.toPointDto(point);
    }

    @Override
    public PointDto showPoint(int id) {
        PointEntity point= new PointEntity();
        point=pointRepository.getById(id);
        return PointMapper.INSTANCE.toPointDto(point);
    }

    @Override
    public List<PointDto> showAllPoint() {
        return Optional.of(pointRepository.findAll())
                .orElse(Collections.emptyList())
                .stream()
                .map(PointMapper.INSTANCE::toPointDto)
                .collect(Collectors.toList());
    }
}

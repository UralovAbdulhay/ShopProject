package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.entity.Detail;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.repository.DetailRepository;
import uz.kapitalbank.testing.service.DetailService;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;

    @Override
    public Detail findByOrderId(Long orderId) {
        return detailRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found by id = " + orderId));
    }

    public Detail save(Detail detail) {
        return detailRepository.save(detail);
    }
}

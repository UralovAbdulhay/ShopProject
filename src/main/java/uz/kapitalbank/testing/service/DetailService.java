package uz.kapitalbank.testing.service;

import uz.kapitalbank.testing.entity.Detail;

public interface DetailService {

    Detail findByOrderId(Long OrderId);


}

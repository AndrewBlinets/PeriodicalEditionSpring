package by.andreiblinets.service.impl;

import by.andreiblinets.dao.PaymentDAO;
import by.andreiblinets.entity.Payment;
import by.andreiblinets.service.PaymentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<Payment> implements PaymentService {

    private static Logger logger = Logger.getLogger(PaymentServiceImpl.class.getName());

    @Autowired
    public PaymentServiceImpl(PaymentDAO paymentDAO)
    {
        super(paymentDAO,logger);
    }
}

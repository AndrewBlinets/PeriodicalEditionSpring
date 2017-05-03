package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.entity.Payment;
import by.andreiblinets.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSimple implements BaseService<Payment> {

    @Autowired
    private BaseDAO<Payment> paymentBaseDAO;

    @Override
    public boolean create(Payment payment) {
        paymentBaseDAO.create(payment); return true;
    }

    @Override
    public boolean update(Payment payment) {
        paymentBaseDAO.update(payment); return true;
    }

    @Override
    public List<Payment> readAll() {
        return paymentBaseDAO.readAll();
    }

    @Override
    public Payment readById(Long id) {
        return paymentBaseDAO.readById(id);
    }

    @Override
    public boolean delete(Payment payment) {
        paymentBaseDAO.delete(payment); return true;
    }
}

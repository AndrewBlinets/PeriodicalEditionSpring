package by.andreiblinets.service;

import by.andreiblinets.entity.Subscription;
import by.andreiblinets.exceptions.ServiceException;

import java.util.List;

public interface SubscriptionService extends BaseService<Subscription> {
    List<Subscription> getSubscribtionByIdUser(long id) throws ServiceException;
}

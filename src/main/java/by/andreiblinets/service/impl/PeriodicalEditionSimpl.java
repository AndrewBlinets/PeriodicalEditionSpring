package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalEditionSimpl implements BaseService<PeriodicalEdition> {

    @Autowired
    private BaseDAO<PeriodicalEdition> periodicalEditionBaseDAO;

    @Override
    public boolean create(PeriodicalEdition periodicalEdition) {
        periodicalEditionBaseDAO.create(periodicalEdition);
        return true;
    }

    @Override
    public boolean update(PeriodicalEdition periodicalEdition) {
        periodicalEditionBaseDAO.update(periodicalEdition);
        return true;
    }

    @Override
    public List<PeriodicalEdition> readAll() {
        return periodicalEditionBaseDAO.readAll();
    }

    @Override
    public PeriodicalEdition readById(Long id) {
        return periodicalEditionBaseDAO.readById(id);
    }

    @Override
    public boolean delete(PeriodicalEdition periodicalEdition) {
        periodicalEditionBaseDAO.delete(periodicalEdition);
        return true;
    }
}

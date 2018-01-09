package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.service.OptionService;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public class OptionServiceImpl implements OptionService {
    @Override
    public void create(Option option) {
        JdbcDaoFactory.getInstance().createOptionDao().create(option);}

    @Override
    public Option findById(int id) {
        return JdbcDaoFactory.getInstance().createOptionDao().findById(id);}

    @Override
    public List<Option> findAll() {
        return JdbcDaoFactory.getInstance().createOptionDao().findAll();}

    @Override
    public void update(Option option) {
        JdbcDaoFactory.getInstance().createOptionDao().update(option);
    }

    @Override
    public void delete(Option option) {
        JdbcDaoFactory.getInstance().createOptionDao().delete(option);}
}

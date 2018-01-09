package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.Assessment;
import ua.kiyv.training.testingSystem.service.AssessmentService;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public class AssessmentServiceImpl implements AssessmentService {
    @Override
    public void create(Assessment assessment) {
        JdbcDaoFactory.getInstance().createAssesmentDao().create(assessment);}

    @Override
    public Assessment findById(int id) {
        return  JdbcDaoFactory.getInstance().createAssesmentDao().findById(id);}

    @Override
    public List<Assessment> findAll() {
        return JdbcDaoFactory.getInstance().createAssesmentDao().findAll();}

    @Override
    public void update(Assessment assessment) {
        JdbcDaoFactory.getInstance().createAssesmentDao().update(assessment);}

    @Override
    public void delete(Assessment assessment) {
        JdbcDaoFactory.getInstance().createAssesmentDao().delete(assessment);}
}

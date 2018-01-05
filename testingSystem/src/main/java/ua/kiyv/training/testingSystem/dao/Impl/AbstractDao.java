package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.DaoException;

/**
 * Created by Tanya on 03.01.2018.
 */
public class AbstractDao {
    void checkForNull(Object entity) throws DaoException {
        if (entity == null) {
//            throw new DaoException().addLogMessage(LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_EMPTY);
        }
    }


//    void checkIsSaved(Identified entity) throws DaoException {
//        if (entity.getId() == 0) {
//            throw new DaoException()
//                    .addLogMessage(LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_UNSAVED + entity);
//        }
//    }
}

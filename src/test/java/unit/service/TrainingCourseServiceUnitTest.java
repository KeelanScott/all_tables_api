package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetTrainingCoursesException;
import org.kainos.ea.model.TrainingCourse;
import org.kainos.ea.service.TrainingCourseService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TrainingCourseServiceUnitTest {
    TrainingCourseDao trainingCourseDao = Mockito.mock(TrainingCourseDao.class);

    TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourseDao);

    TrainingCourse trainingCourse = new TrainingCourse(
            1,
            "TrainingCourse 1",
            "description"
    );

    @Test
    void getAllTrainingCourses_shouldReturnTrainingCourseList_whenDaoReturnsTrainingCourseList() throws FailedToGetTrainingCoursesException, SQLException, DatabaseConnectionException {
        ArrayList<TrainingCourse> list = new ArrayList<>();
        list.add(trainingCourse);
        list.add(trainingCourse);
        list.add(trainingCourse);

        Mockito.when(trainingCourseDao.getAllTrainingCourses()).thenReturn(list);

        assertEquals(list, trainingCourseService.getAllTrainingCourses());
    }

    @Test
    void getAllTrainingCourses_shouldThrowFailedToGetTrainingCourseException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(trainingCourseDao.getAllTrainingCourses()).thenThrow(SQLException.class);

        assertThrows(FailedToGetTrainingCoursesException.class, () -> trainingCourseService.getAllTrainingCourses());
    }

    @Test
    void getAllTrainingCourses_shouldThrowFailedToGetTrainingCourseException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(trainingCourseDao.getAllTrainingCourses()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetTrainingCoursesException.class, () -> trainingCourseService.getAllTrainingCourses());
    }
}
package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exceptions.FailedToGetTrainingCoursesException;
import org.kainos.ea.models.TrainingCourse;
import org.kainos.ea.services.TrainingCourseService;
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
    void getAllTrainingCourses_shouldReturnTrainingCourseList_whenDaoReturnsTrainingCourseList() throws FailedToGetTrainingCoursesException, SQLException {
        ArrayList<TrainingCourse> list = new ArrayList<>();
        list.add(trainingCourse);
        list.add(trainingCourse);
        list.add(trainingCourse);

        Mockito.when(trainingCourseDao.getAllTrainingCourses()).thenReturn(list);

        assertEquals(list, trainingCourseService.getAllTrainingCourses());
    }

    @Test
    void getAllTrainingCourses_shouldThrowFailedToGetTrainingCourseException_whenDaoThrowsSQLException() throws SQLException{
        Mockito.when(trainingCourseDao.getAllTrainingCourses()).thenThrow(SQLException.class);

        assertThrows(FailedToGetTrainingCoursesException.class, () -> trainingCourseService.getAllTrainingCourses());
    }

}
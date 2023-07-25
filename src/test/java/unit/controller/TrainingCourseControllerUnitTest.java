
package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controllers.TrainingCourseController;
import org.kainos.ea.exceptions.FailedToGetTrainingCoursesException;
import org.kainos.ea.models.TrainingCourse;
import org.kainos.ea.services.TrainingCourseService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TrainingCourseControllerUnitTest {
    TrainingCourseService trainingCourseService = Mockito.mock(TrainingCourseService.class);
    TrainingCourseController trainingCourseController = new TrainingCourseController(trainingCourseService);

    TrainingCourse trainingCourse = new TrainingCourse(
            1,
            "TrainingCourse 1",
            "description"
    );



    @Test
    void getAllTrainingCourses_shouldReturnOK_whenServiceReturnsList() throws FailedToGetTrainingCoursesException {

        List<TrainingCourse> sampleTrainingCourses = new ArrayList<>();
        sampleTrainingCourses.add(trainingCourse);
        sampleTrainingCourses.add(trainingCourse);
        sampleTrainingCourses.add(trainingCourse);

        Mockito.when(trainingCourseService.getAllTrainingCourses()).thenReturn(sampleTrainingCourses);

        Response response = trainingCourseController.getAllTrainingCourses();
        assert(response.getStatus() == 200);
    }

    @Test
    void getAllTrainingCourses_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetTrainingCoursesException {
        Mockito.when(trainingCourseService.getAllTrainingCourses()).thenThrow(FailedToGetTrainingCoursesException.class);

        Response response = trainingCourseController.getAllTrainingCourses();
        assert(response.getStatus() == 500);
    }

}

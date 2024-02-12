package finaljavaproject.Displays;

import java.io.IOException;
import java.util.List;

import finaljavaproject.Members.StoreMember;
import finaljavaproject.Products.Dvd;

/*
 * Interface to help for display operations
 */
public interface IDisplayer {
    public void handleUserTasks(int userTask)throws IOException;
    public void displayAvailableOptions();
    public int getUserTask(int minNumTask, int maxNumTask)throws IOException;
}


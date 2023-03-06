

import java.lang.reflect.Array;
import java.util.*;

public class Project {
    private final String name;
    private final List<Task> tasks;

    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * Schedule all tasks within this project such that they will be completed as early as possible.
     *
     * @return An integer array consisting of the earliest start days for each task.
     */
    public int[] getEarliestSchedule() {

        int[] taskSchedule = new int[tasks.size()];
        for (int i = 0; i < taskSchedule.length; i++) {
            taskSchedule[i] = 0;
        }

        int i = 0;
        Map<Integer,Integer> taskEndtime = new HashMap<>();

        taskEndtime.put(0,tasks.get(0).getDuration());

        taskSchedule[i] = tasks.get(0).getTaskID();

        i += 1;

        while (taskSchedule[taskSchedule.length - 2] == 0) {

            for (Task task : tasks) {
                boolean canRun = true;

                if(task.getTaskID() == 0){
                    if(taskEndtime.keySet().contains(task.getTaskID())){
                        canRun = false;
                    }
                }
                for (Integer dependency : task.getDependencies())
                {

                    if (!taskEndtime.keySet().contains(dependency) || taskEndtime.keySet().contains(task.getTaskID()))
                    {
                        canRun = false;
                    }
                }

                if (canRun) {

                    int maxDuration = 0;

                    for (Integer dependency : task.getDependencies()) {

                        if (taskEndtime.get(dependency) > maxDuration) {
                            maxDuration = taskEndtime.get(dependency);
                        }
                    }
                    taskEndtime.put(task.getTaskID(), maxDuration+task.getDuration());
                    taskSchedule[i] = task.getTaskID() ;

                    i+=1;


                }

            }
        }
        for(int j = 0;j<taskEndtime.size();j++){
            taskSchedule[j] = taskEndtime.get(j) - tasks.get(j).getDuration();
        }


        return taskSchedule;
    }

    /**
     * @return the total duration of the project in days
     */
    public int getProjectDuration() {
        int projectDuration = 0;
        return projectDuration;
    }

    public static void printlnDash(int limit, char symbol) {
        for (int i = 0; i < limit; i++) System.out.print(symbol);
        System.out.println();
    }

    public void printSchedule(int[] schedule) {
        int limit = 65;
        char symbol = '-';
        printlnDash(limit, symbol);
        System.out.println(String.format("Project name: %s", name));
        printlnDash(limit, symbol);

        // Print header
        System.out.println(String.format("%-10s%-45s%-7s%-5s", "Task ID", "Description", "Start", "End"));
        printlnDash(limit, symbol);
        for (int i = 0; i < schedule.length; i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%-10d%-45s%-7d%-5d", i, t.getDescription(), schedule[i], schedule[i] + t.getDuration()));
        }
        printlnDash(limit, symbol);
        System.out.println(String.format("Project will be completed in %d days.", tasks.get(schedule.length - 1).getDuration() + schedule[schedule.length - 1]));
        printlnDash(limit, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        int equal = 0;

        for (Task otherTask : ((Project) o).tasks) {
            if (tasks.stream().anyMatch(t -> t.equals(otherTask))) {
                equal++;
            }
        }

        return name.equals(project.name) && equal == tasks.size();
    }

}

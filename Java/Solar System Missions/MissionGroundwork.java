

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public class MissionGroundwork {

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        for (Project project : projectList) {
            int[] a =project.getEarliestSchedule();
            project.printSchedule(a);

        }

        }

        // TODO: YOUR CODE HERE


    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        try {
            List<Project> projectList = new ArrayList<>();
            List<Task> taskList = new ArrayList<>();
            List<String> projectNames = new ArrayList<>();
            File file = new File(filename);
            int c = 0;
//an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Task");
            NodeList nodeList2 = doc.getElementsByTagName("Project");
            for (int itr3 = 0; itr3 < nodeList2.getLength(); itr3++) {
                Node node3 = nodeList2.item(itr3);

                if (node3.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement3 = (Element) node3;
                    String name = eElement3.getElementsByTagName("Name").item(0).getTextContent();
                    projectNames.add(name);
                }
            }

// nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    int taskID = Integer.parseInt(eElement.getElementsByTagName("TaskID").item(0).getTextContent());
                    String description = eElement.getElementsByTagName("Description").item(0).getTextContent();
                    int duration = Integer.parseInt(eElement.getElementsByTagName("Duration").item(0).getTextContent());
                    NodeList dependenciesList = eElement.getElementsByTagName("Dependencies");
                    List<Integer> dependencies = new ArrayList<>();
                    int a = 0;
                    String b = "";
                    for (int temp2 = 0; temp2 < dependenciesList.getLength(); temp2++) {
                        b = ((Element) dependenciesList.item(temp2)).getTextContent();
                        b = b.replaceAll(" ", "");
                        b = b.replaceAll("\t", "");
                        b = b.replaceAll("\n", "");
                        for (int i = 0; i < b.length(); i++) {
                            int ia = b.charAt(i) - '0';
                            dependencies.add(ia);
                        }
                    }

                    if (taskID == 0 && taskList.size() > 0) {
                        projectList.add(new Project(projectNames.get(c), taskList));
                        c += 1;
                        taskList = new ArrayList<>();
                    }

                    taskList.add(new Task(taskID, description, duration, dependencies));

                }
            }
            // TODO: YOUR CODE HERE
            projectList.add(new Project(projectNames.get(c), taskList));
            return projectList;
        }
        catch(Exception e){

        }
        return null;
    }



}

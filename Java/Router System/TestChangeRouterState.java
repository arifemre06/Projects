
import java.io.FileNotFoundException;
import java.util.List;

public class TestChangeRouterState {
    public static void main(String[] args) {
        performTest(args);
    }

    static void performTest(String[] args) {
        try {
            List<RoutingTable> expectedRoutingTableList = (List<RoutingTable>) TestUtils.readDatObject(TestUtils.TEST_CHANGE_ROUTER_STATE_OUTPUT_FILE);
            double score = 0;
            TestUtils.disableOutput();

            Network network = new Network(args[0]);
            Router router = network.getRouterWithIp("192.168.1.13");
            if (router != null) {
                network.changeStateOfRouter(router, true);
                List<RoutingTable> routingTableListUUT = network.getRoutingTablesOfAllRouters();
                score = TestUtils.compareRoutingTables(expectedRoutingTableList, routingTableListUUT);
            }

            TestUtils.enableOutput();
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

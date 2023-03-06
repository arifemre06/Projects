

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Network implements Serializable {
    static final long serialVersionUID = 55L;
    private List<Router> routers = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * The constructor should read the given file and generate necessary Router and Link objects and initialize link
     * and router arrays.
     * Also, you should implement Link class’s calculateAndSetCost() method in order for the costs to be calculated
     * based on the formula given in the instructions.
     *
     * @param filename Input file to generate the network from
     * @throws FileNotFoundException
     */
    public Network(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.charAt(0) == 'R')
            {
                line = line.split(":")[1];
                System.out.println(line);
                addRouter(new Router(line,this));
            }
            else if(line.charAt(0) == 'L'){

                String ips = line.split(":")[1];
                String bandwidth = line.split(":")[2];
                bandwidth = bandwidth.split(" ")[0];
                ips = ips.split(" ")[0];
                String ip1 = ips.split("-")[0];
                String ip2 = ips.split("-")[1];
                System.out.println(ip1+ " "+ip2);
                System.out.println(bandwidth);
                int bandwidthmbps = Integer.parseInt(bandwidth);
                addLink(new Link(ip1,ip2,bandwidthmbps));
            }
        }

        // TODO: YOUR CODE HERE
        updateAllRoutingTables();
    }

    /**
     * IP address of the router should be placed in group 1
     * Subnet of the router should be placed group 2
     *
     * @return regex for matching RouterIP lines
     */
    public static String routerRegularExpression() {
        // TODO: REGEX HERE
        return "";
    }

    /**
     * IP address of the router 1 should be placed in group 1
     * IP address of the router 2 should be placed in group 2
     * Bandwidth of the link should be placed in group 3
     *
     * @return regex for matching Link lines
     */
    public static String linkRegularExpression() {
        // TODO: REGEX HERE
        return "";
    }

    public List<Router> getRouters() {
        return routers;
    }

    public List<Link> getLinks() {
        return links;
    }

    public List<RoutingTable> getRoutingTablesOfAllRouters() {
        if (routers != null) {
            List<RoutingTable> routingTableList = new ArrayList<>();
            for (Router router : routers)
                routingTableList.add(router.getRoutingTable());
            return routingTableList;
        }
        return null;
    }

    public Router getRouterWithIp(String ip) {
        if (routers != null) {
            for (Router router : routers) {
                if (router.getIpAddress().equals(ip))
                    return router;
            }
        }
        return null;
    }

    public Link getLinkBetweenRouters(String ipAddr1, String ipAddr2) {
        if (links != null) {
            for (Link link : links) {
                if (link.getIpAddress1().equals(ipAddr1) && link.getIpAddress2().equals(ipAddr2)
                        || link.getIpAddress1().equals(ipAddr2) && link.getIpAddress2().equals(ipAddr1))
                    return link;
            }
        }
        return null;
    }

    public List<Link> getLinksOfRouter(Router router) {
        List<Link> routersLinks = new ArrayList<>();
        if (links != null) {
            for (Link link : links) {
                if (link.getIpAddress1().equals(router.getIpAddress()) ||
                        link.getIpAddress2().equals(router.getIpAddress())) {
                    routersLinks.add(link);
                }
            }
        }
        return routersLinks;
    }

    public void updateAllRoutingTables() {
        for (Router router : getRouters()) {
            router.getRoutingTable().updateTable();
        }
    }

    /**
     * Changes the cost of the link with a new value, and update all routing tables.
     *
     * @param link    Link to update
     * @param newCost New link cost
     */
    public void changeLinkCost(Link link, double newCost) {
        // TODO: YOUR CODE HERE
    }

    /**
     * Add a new Link to the Network, and update all routing tables.
     *
     * @param link Link to be added
     */
    public void addLink(Link link) {
        links.add(link);
        updateAllRoutingTables();
        // TODO: YOUR CODE HERE
    }

    /**
     * Remove a Link from the Network, and update all routing tables.
     *
     * @param link Link to be removed
     */
    public void removeLink(Link link) {
        links.remove(link);
        updateAllRoutingTables();
        // TODO: YOUR CODE HERE
    }

    /**
     * Add a new Router to the Network, and update all routing tables.
     *
     * @param router Router to be added
     */
    public void addRouter(Router router) {
        routers.add(router);

        updateAllRoutingTables();
        // TODO: YOUR CODE HERE
    }

    /**
     * Remove a Router from the Network, and update all routing tables. Beware that removing a router also causes the
     * removal of any links connected to it from the Network. Also beware that a router which was removed should not
     * appear in any routing table entry.
     *
     * @param router Router to be removed
     */
    public void removeRouter(Router router) {
        routers.remove(router);
        updateAllRoutingTables();
        // TODO: YOUR CODE HERE
    }

    /**
     * Change the state of the router (down or live), and update all routing tables. Beware that a router which is down
     * should not be reachable and should not appear in any routing table entry’s path. However, this router might appear
     * in other router’s routing-tables as a separate entry with a totalRouteCost=Infinity value because it was not
     * completely removed from the network.
     *
     * @param router Router to update
     * @param isDown New status of the router
     */
    public void changeStateOfRouter(Router router, boolean isDown) {
        // TODO: YOUR CODE HERE
    }

}

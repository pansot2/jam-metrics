/*
 * Copyleft 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jam.metrics;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jam.metrics.applicationmetricsapi.MetricsCacheApi;
import org.jam.metrics.applicationmetricsapi.MetricsPropertiesApi;
import org.jam.metrics.applicationmetricslibrary.DeploymentMetricProperties;
import org.jam.metrics.applicationmetricslibrary.MetricInternalParameters;
import org.jam.metrics.applicationmetricsproperties.MetricProperties;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
public class PrintMetrics extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private MetricsApiSessionBean metricsApiSessionBean;

    private String groupName = "myTestGroup";
    private HashMap<String, JFrame> frames = new HashMap<String, JFrame>();
    HashMap<String, Plot2DPanel> plots = new HashMap<String, Plot2DPanel>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        initializeMetricProperties();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrintMetrics</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrintMetrics : </h1>");
            metricsApiSessionBean.countMethod();
            metricsApiSessionBean.countMethod();
            metricsApiSessionBean.countMethod();
            metricsApiSessionBean.countMethod();
            metricsApiSessionBean.countMethod();
            out.println("<br>Successful Run ...</br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void initializeMetricProperties() {
        MetricProperties metricProperties = new MetricProperties();
        metricProperties.setCacheStore("false");
        metricProperties.setMetricPlot("true");
        DeploymentMetricProperties.getDeploymentMetricProperties().addDeploymentInternalParameters(groupName, new MetricInternalParameters());
        initializePlots(true);
        metricProperties.setPlots(plots);
        metricProperties.setFrames(frames);
        metricProperties.addColor("red", Color.RED);
        metricProperties.addColor("blue", Color.BLUE);
        metricProperties.addColor("green", Color.GREEN);
        metricProperties.addColor("yellow", Color.YELLOW);
        metricProperties.addColor("magenta", Color.MAGENTA);
        metricProperties.addColor("cyan", Color.CYAN);
        metricProperties.setPlotRefreshRate(1);
        MetricsPropertiesApi.storeProperties(groupName, metricProperties);
    }

    private void initializePlots(boolean init) {
        if (init) {
            HashMap<String, Plot2DPanel> metricPlots;
            HashMap<String, JFrame> metricFrames;

            MetricProperties properties = DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(groupName);

            if (properties == null || properties.getPlots().isEmpty()) {
                metricPlots = new HashMap<String, Plot2DPanel>();
            } else {
                metricPlots = properties.getPlots();
            }

            if (properties == null || properties.getFrames().isEmpty()) {
                metricFrames = new HashMap<String, JFrame>();
            } else {
                metricFrames = properties.getFrames();
            }

            boolean create = true;
            if (properties != null && properties.getPlots().containsKey("plot1") && properties.getFrames().get("plot1") != null) {
                create = false;
            }

            if (create) {
                Plot2DPanel plot = new Plot2DPanel("SOUTH");

                JFrame frame = new JFrame("Plot 1");
                frame.setSize(600, 600);
                frame.setContentPane(plot);
                frame.setVisible(true);
                frame.setResizable(true);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent evt) {
                        DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(groupName).getFrames().remove("plot1");
                        DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(groupName).getPlots().remove("plot1");
                    }
                });

                metricPlots.put("plot1", plot);
                frames.put("plot1", frame);
            }

            create = true;
            if (properties != null && properties.getPlots().containsKey("plot2") && properties.getFrames().get("plot2") != null) {
                create = false;
            }

            if (create) {
                Plot2DPanel plot2 = new Plot2DPanel("SOUTH");

                JFrame frame2 = new JFrame("Plot 2");
                frame2.setSize(600, 600);
                frame2.setContentPane(plot2);
                frame2.setVisible(true);
                frame2.setResizable(true);
                frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame2.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent evt) {
                        DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(groupName).getFrames().remove("plot2");
                        DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(groupName).getPlots().remove("plot2");
                    }
                });

                metricPlots.put("plot2", plot2);
                frames.put("plot2", frame2);
            }
            
            plots = metricPlots;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

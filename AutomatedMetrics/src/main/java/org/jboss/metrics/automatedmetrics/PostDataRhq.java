/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.metrics.automatedmetrics;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import org.jboss.metrics.automatedmetrics.utils.DoubleValue;

/**
 *
 * @author panos
 */
public interface PostDataRhq {

    @PUT
    @Path("/rest/metric/data/{id}/raw/{timeStamp}")
    @Consumes("application/json")
    void postDataRhq(DoubleValue data, @PathParam("id") int id, @PathParam("timeStamp") long timestamp, @HeaderParam(HttpHeaders.ACCEPT) String accept);
    
    @POST
    @Path("/rest/content/fresh")
    @Consumes("application/octet-stream") 
    @Produces("application/json")
    String postTestContentDataRhq(byte[] data, @HeaderParam(HttpHeaders.CONTENT_TYPE) String contentType, @HeaderParam(HttpHeaders.ACCEPT) String accept);
    
    @PUT
    @Path("/rest/content/{handle}/plugins")
    @Consumes("application/json")
    void putTestContentDataRhq(@PathParam("handle") String handle, @QueryParam("name") String name, @QueryParam("scan") String scan, @HeaderParam(HttpHeaders.ACCEPT) String accept);
    
    @GET
    @Path("/rest/metric/data/{id}")
    @Consumes("application/json")
    String getScheduleId(@PathParam("id") int id, @HeaderParam(HttpHeaders.ACCEPT) String accept);
}

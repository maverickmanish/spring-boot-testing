package com.rest.order.controllers;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

public class GremlinRunner {
    public static void main(String[] args) throws Exception {

//        Cluster open = Cluster.open();
//        Client client = open.connect();
//        ResultSet resultSet = client.submit("g.addV('AdminRoleEntity').property('roleName','admin_editor')");
//        ResultSet resultSet2 = client.submit("g.V().count()");
//        Result result = resultSet.one();
//        System.out.println(result);
//        System.out.println("connecting ..."+ resultSet2.one().getInt());

//        Map<String,Object> params = new HashMap<>();
//        params.put("name","marko");
//        List<Result> list = client.submit("g.V().has('person','name',name).out('knows')", params).all().get();
//        List<Vertex> list2 = g.V().has("person","name","marko").out("knows").toList();

// bytecode
        GraphTraversalSource g = traversal().withRemote(DriverRemoteConnection.using("localhost",8182,"g"));

       g.addV("AdminUserEntity").property("name","admin").property("employeeCode","12354").property("email","admin.akshay@nagarro.com").property("phoneNumber","8960796843")
               .addV("AdminUserEntity").property("name","editor").property("employeeCode","45675").property("email","admin.dev@nagarro.com").property("phoneNumber","5678903456")
       .addV("AdminUserEntity").property("name","viewer").property("employeeCode","45676").property("email","admin.prashant@nagarro.com").property("phoneNumber","5869068590")
                .next();
//
//
//          g.addV("AdminRoleEntity").property("roleName","Super Admin")
//                 .addV("AdminRoleEntity").property("roleName","Manager")
//                  .addV("AdminRoleEntity").property("roleName","Agent")
//   .next();

//         g.addE("HAS_ROLE").from(__.V(128)).to(__.V(4))
//         g.addE("HAS_ROLE").from(__.V(133)).to(__.V(0))
//         g.addE("HAS_ROLE").from(__.V(144)).to(__.V(2))

        g.addV("AdminPrivilegeEntity").property("sortId",13).property("uiPrivilegeId",13).property("privilegeName","Moderation - Remove Post").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",15).property("uiPrivilegeId",15).property("privilegeName","Add Tag Group").property("selected",false).property("Agent",false).property("Manager",false).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",16).property("uiPrivilegeId",16).property("privilegeName","Add Tag").property("selected",false).property("Agent",false).property("Manager",false).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",17).property("uiPrivilegeId",17).property("privilegeName","Delete Tag Group").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",18).property("uiPrivilegeId",18).property("privilegeName","Delete Tag").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",20).property("uiPrivilegeId",20).property("privilegeName","Role Management Edit").property("selected",false).property("Agent",false).property("Manager",false).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",4).property("uiPrivilegeId",4).property("privilegeName","Content View").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",6).property("uiPrivilegeId",6).property("privilegeName","User Content Access View").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",8).property("uiPrivilegeId",8).property("privilegeName","View HCP Listing").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",12).property("uiPrivilegeId",12).property("privilegeName","Moderation - View Post").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",14).property("uiPrivilegeId",14).property("privilegeName","View Tag").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",19).property("uiPrivilegeId",19).property("privilegeName","Role Management View").property("selected",false).property("Agent",true).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",1).property("uiPrivilegeId",1).property("privilegeName","Content Upload").property("selected",false).property("Agent",false).property("Manager",false).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",7).property("uiPrivilegeId",7).property("privilegeName","Add HCP").property("selected",false).property("Agent",false).property("Manager",false).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",2).property("uiPrivilegeId",2).property("privilegeName","Content Edit").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",3).property("uiPrivilegeId",3).property("privilegeName","Content Deletion").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",5).property("uiPrivilegeId",5).property("privilegeName","User Content Access Edit").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",9).property("uiPrivilegeId",9).property("privilegeName","Block HCP").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",11).property("uiPrivilegeId",11).property("privilegeName","Export HCP List").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true)
                .addV("AdminPrivilegeEntity").property("sortId",10).property("uiPrivilegeId",10).property("privilegeName","Edit HCP").property("selected",false).property("Agent",false).property("Manager",true).property("Super Admin",true).next();

        g.addV("AdminUserPrivilegeEntity").property("privileges","content_upload,content_edit,content_deletion,content_view,user_content_access_edit,user_content_access_view,add_hcp,view_hcp_listing,block_hcp,edit_hcp,export_hcp_list,moderation_view_post,moderation_remove_post,view_tag,add_tag_group,add_tag,delete_tag_group,delete_tag,role_management_view,role_management_edit")
                .next();
              g.addV("AdminUserPrivilegeEntity").property("privileges","4cc31557-7a11-85ec-5996-7df0ad7a78d1,f2c31557-7a12-85c0-c639-f36d2e48b9ec,ccc31557-7a15-018f-efd8-4219cae55fb8,cec31557-7a16-184c-9224-f4f7ed90c9bb,f2c31557-7a17-86e8-aef5-b773d9f595db,02c31557-7a19-b299-e321-5732b7bb00b2")
                .next();
       // g.addE("HAS_PRIVILEGES").from(__.V(128)).to(__.V(126))
       // g.addE("HAS_PRIVILEGES").from(__.V(133)).to(__.V(139))


        //g.V().drop().next();

//           %%gremlin
//        g.addV("AdminUserEntityTest").property("name","manish").addE("HAS_PRIVILEGES").to(__.addV("APUEntity").property("privileges","dddd"))


        List<Object> objects = g.V().values().toList();
        System.out.println("result"+objects);



//        open.close();
    }
}

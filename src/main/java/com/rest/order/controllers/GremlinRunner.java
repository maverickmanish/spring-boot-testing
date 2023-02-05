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

// 128       g.addV("AdminUserEntity").property("name","admin").property("employeeCode","12354").property("email","admin.akshay@nagarro.com").property("phoneNumber","8960796843")
// 133               .addV("AdminUserEntity").property("name","editor").property("employeeCode","45675").property("email","admin.dev@nagarro.com").property("phoneNumber","5678903456")
// 144          g.addV("AdminUserEntity").property("name","viewer").property("employeeCode","45676").property("email","admin.prashant@nagarro.com").property("phoneNumber","5869068590")
//                .next();


//  0          g.addV("AdminRoleEntity").property("roleName","admin_editor")
//  2                  .addV("AdminRoleEntity").property("roleName","admin_viewer")
//   4                 .addV("AdminRoleEntity").property("roleName","superadmin").next();

        // g.addE("HAS_ROLE").from(__.V(128)).to(__.V(4))
        // g.addE("HAS_ROLE").from(__.V(133)).to(__.V(0))
        // g.addE("HAS_ROLE").from(__.V(144)).to(__.V(2))

//        g.addV("AdminPrivilegeEntity").property("privilegeName","moderation_remove_post").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","add_tag_group").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",false).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","add_tag").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",false).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","delete_tag_group").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","delete_tag").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","role_management_edit").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",false).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","content_view").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","user_content_access_view").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","view_hcp_listing").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","moderation_view_post").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","view_tag").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","role_management_view").property("selected",false).property("isViewPrivilege",true).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","content_upload").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",false).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","add_hcp").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",false).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","content_edit").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","content_deletion").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","user_content_access_edit").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","block_hcp").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","export_hcp_list").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true)
//                .addV("AdminPrivilegeEntity").property("privilegeName","edit_hcp").property("selected",false).property("isViewPrivilege",false).property("isEditorPrivilege",true).property("isAdminPrivilege",true).next();
//
//        g.addV("AdminUserPrivilegeEntity").property("privileges","content_upload,content_edit,content_deletion,content_view,user_content_access_edit,user_content_access_view,add_hcp,view_hcp_listing,block_hcp,edit_hcp,export_hcp_list,moderation_view_post,moderation_remove_post,view_tag,add_tag_group,add_tag,delete_tag_group,delete_tag,role_management_view,role_management_edit")
//                .next();
//              g.addV("AdminUserPrivilegeEntity").property("privileges","content_edit,content_deletion,content_view,user_content_access_edit,user_content_access_view,view_hcp_listing,block_hcp,edit_hcp,export_hcp_list,moderation_view_post,moderation_remove_post,view_tag,delete_tag_group,delete_tag,role_management_view")
//                .next();
       // g.addE("HAS_PRIVILEGES").from(__.V(128)).to(__.V(126))
       // g.addE("HAS_PRIVILEGES").from(__.V(133)).to(__.V(139))


        //g.V().drop().next();

        List<Object> objects = g.V().values().toList();
        System.out.println("result"+objects);



//        open.close();
    }
}

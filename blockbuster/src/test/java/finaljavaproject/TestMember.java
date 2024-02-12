package finaljavaproject;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import finaljavaproject.Members.Employee;
import finaljavaproject.Members.MemberManager;
import finaljavaproject.Members.StoreMember;

public class TestMember {
    List <StoreMember> members = new ArrayList<StoreMember>();
    MemberManager member = new MemberManager(members);

    @Test
    public void testIfAddsMember() {
        StoreMember employee = new Employee("Lila", "Trokichkina", "lila.431@gmail.com");
        member.addMember(employee);
        assertEquals(employee,member.findMemberByEmail("lila.431@gmail.com"));
    }
    @Test
    public void testIfRemovesMember() {
        StoreMember employee = new Employee("Lila", "Trokichkina", "lila.431@gmail.com");
        members.add(employee);
        member.removeMember(employee);
        assertEquals(null,member.findMemberByEmail("lila.431@gmail.com"));
    }
    @Test
    public void testIfgetsRightMember() {
        StoreMember employee = new Employee("Lila", "Trokichkina", "lila.431@gmail.com");
        members.add(employee);
        assertEquals(employee,member.getMember(0));
    }
    
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import data.*;
import utility.Console;

import org.junit.Test;

/**
 * Test Relationship implementation
 * @author Matt Fossett
 *
 */
public class RelationshipTest {
	
	//UMLEnvironment env = new UMLEnvironment();
	Console c = new Console();
	UMLEnvironment env = c.getUMLEnvironment();
	
	/**
	 * Friendly way to run commands to the console
	 * @param command
	 */
	public void run(String command) {
		String[] arr = command.split(" ");
		c.checkInput(arr);
	}
	
	/**
	 * Regular test for relationships
	 */
	@Test
	public void testRelationship() {
		env.addItem(new UMLItem("Matt"));
		env.addItem(new UMLItem("Eric"));
		
		Relationship r = new Relationship(env.findItem("Matt"), env.findItem("Eric"));
		
		env.addRelationship(r);
		
		assertTrue(env.findRelationship(r) != null);
		
		env.addRelationship(r);
		
		assertEquals(1, env.getRelationships().size());
		
		env.addItem(new UMLItem("Lauren"));
		
		r = new Relationship(env.findItem("Eric"), env.findItem("Lauren"));
		env.addRelationship(r);
		System.out.println(env.listRelationships());
		r = new Relationship(env.findItem("Eric"), null);
		assertTrue(env.findRelationship(r) == null);
		
		r = new Relationship(env.findItem("Eric"), env.findItem("Lauren"));
		
		
		r = new Relationship(env.findItem("Eric"), null);
		assertTrue( !env.removeRelationship(r) );
		r = new Relationship(env.findItem("Eric"), env.findItem("Lauren"));
		
		assertTrue( env.removeRelationship(r) );
		
		r.setQuantifier(2);
		env.addRelationship(r);
		System.out.println(env.listRelationships());
		
	}
	
	/**
	 * Run console commands to test relationships
	 */
	@Test
	public void consoleRelationships() {
		run("add Matt");
		run("add Eric");
		run("add_relationship Matt Eric 1t1");
		Relationship test = env.findRelationship(env.findItem("Matt"), env.findItem("Eric"));
		
		assertTrue(test != null);
		assertTrue(env.findRelationship(env.findItem("Matt"), env.findItem("Deric")) == null);
		
		assertTrue(env.findRelationship(env.findItem("Matt"), null) == null);
		assertTrue(env.findRelationship(null, env.findItem("Deric")) == null);
		assertTrue(env.findRelationship(null, env.findItem("Eric")) == null);
		assertTrue(env.findRelationship(null, null) == null);
		
		run("edit_relationship Matt Eric MtM");
		Relationship rel = new Relationship(env.findItem("Matt"), env.findItem("Eric"));
		assertEquals("MtM" , env.findRelationship(rel).getQuantifierName() );
		
		run("remove_relationship Matt Eric");
		assertEquals(0, env.getRelationships().size());
		run("add A");
		run("add B");
		run("add_relationship A B");
		assertEquals(1, env.getRelationships().size());
		run("add_relationship A B");
		assertEquals(1, env.getRelationships().size());
		run("edit_relationship A B N");
		assertEquals("N", env.findRelationship(new Relationship(env.findItem("A"), env.findItem("B"))).getQuantifierName());
	}
}

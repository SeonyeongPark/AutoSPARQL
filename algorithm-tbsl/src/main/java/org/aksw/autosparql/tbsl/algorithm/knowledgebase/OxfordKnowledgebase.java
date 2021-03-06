package org.aksw.autosparql.tbsl.algorithm.knowledgebase;

import java.io.IOException;
import org.aksw.autosparql.commons.index.Indices;
import org.dllearner.common.index.MappingBasedIndex;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OxfordKnowledgebase extends LocalKnowledgebase
{
	public static final OxfordKnowledgebase INSTANCE = new OxfordKnowledgebase();
 	
	static protected Model oxfordModel;	
	static protected Model getOxfordModel()
	{
		if(oxfordModel==null)
		{
			oxfordModel = ModelFactory.createMemModelMaker().createDefaultModel();
			oxfordModel.read(OxfordKnowledgebase.class.getClassLoader().getResourceAsStream("oxford.ntriples"),null,"N-TRIPLE");
		}
		return oxfordModel;
	}
	
	private static MappingBasedIndex createMappingIndex()
	{
		try
		{
			return new MappingBasedIndex(
					OxfordKnowledgebase.class.getClassLoader().getResourceAsStream("tbsl/oxford_class_mappings.txt"), 
					OxfordKnowledgebase.class.getClassLoader().getResourceAsStream("tbsl/oxford_resource_mappings.txt"),
					OxfordKnowledgebase.class.getClassLoader().getResourceAsStream("tbsl/oxford_dataproperty_mappings.txt"),
					OxfordKnowledgebase.class.getClassLoader().getResourceAsStream("tbsl/oxford_objectproperty_mappings.txt")
					);
		}
		catch (IOException e) {throw new RuntimeException(e);}
	}	

	private OxfordKnowledgebase() {	super(getOxfordModel(),"oxford","Oxford Houses",new Indices(getOxfordModel(),createMappingIndex()));}
}
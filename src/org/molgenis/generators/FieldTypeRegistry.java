package org.molgenis.generators;

import java.util.Map;
import java.util.TreeMap;

import org.molgenis.generators.fieldtypes.FieldType;
import org.molgenis.model.MolgenisModelException;
import org.molgenis.model.elements.Field;

/**
 * This class enables generator developers to add new AbstractField variants easily.
 * All particular details for a fieldType are encapsulated in this class. <br>
 * @see FieldType interface
 * 
 * @author Morris Swertz
 */
public class FieldTypeRegistry
{
	Map<String, FieldType> types = new TreeMap<String, FieldType>();

	public void addType(FieldType ft)
	{
		types.put(ft.getClass().getSimpleName().toLowerCase(), ft);
		ft.setRegistry(this);
	}

	public FieldType get(Field f) throws MolgenisModelException
	{
		try
		{
			String fieldType = f.getType() + "field";
			FieldType ft = types.get(fieldType);
			ft = ft.getClass().newInstance();
			ft.setField(f);
			ft.setRegistry(this);
			return ft;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MolgenisModelException(e.getMessage());
		}
	}
}

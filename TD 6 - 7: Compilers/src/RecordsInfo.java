import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.Pair;

import edu.polytechnique.mjava.ast.TType;
import edu.polytechnique.mjava.ast.TTypeDef;

public class RecordsInfo {
	public TTypeDef             definition;
	public int                  size;
	public Map<String, Integer> offsets;

	public RecordsInfo(TTypeDef definition, int size, Map<String, Integer> offsets)
	{
		this.definition = definition;
		this.size = size;
		this.offsets = offsets;
	}

	public static void visit1(Map<String, TTypeDef> mtypes,
			Map<String, RecordsInfo> infos,
			TTypeDef ty)
	{
		// Compute the informations (size & field offsets) of `ty`
		// and store it in `infos`. The map `mtypes` contains all
		// the records declarations.
		
		ty.getParent().ifPresent(parent -> visit1(mtypes, infos, parent));
		
		int tsize = 0;
		if(ty.getParent().isPresent()) tsize += infos.get(ty.getParent().get().getName()).size;
		
		Map<String, Integer> toffsets = new HashMap<String, Integer>();
		ty.getParent().ifPresent(parent -> toffsets.putAll(infos.get(parent.getName()).offsets));
		
		ty.getFields();
		for (Pair<TType, String> field : ty.getFields()) {
			toffsets.put(field.b, tsize);
			tsize += field.a.isBase() ? 
					1 : infos.getOrDefault(field.a.asNamed().getName(), 
							new RecordsInfo(null, 1, null)).size;
		}
		infos.put(ty.getName(), new RecordsInfo(ty, tsize, toffsets));
	}

	public static Map<String, RecordsInfo> visit(List<TTypeDef> types) {
		Map<String, TTypeDef> mtypes
		= new HashMap<String, TTypeDef>();
		Map<String, RecordsInfo> result
		= new HashMap<String, RecordsInfo>();

		for (TTypeDef def : types)
			mtypes.put(def.getName(), def);
		for (TTypeDef def : types)
			visit1(mtypes, result, def);
		return result;
	}
}

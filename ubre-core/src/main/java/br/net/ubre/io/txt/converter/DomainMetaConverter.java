package br.net.ubre.io.txt.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.net.ubre.data.var.ValueType;
import br.net.ubre.framework.Context;
import br.net.ubre.framework.ContextElement;
import br.net.ubre.framework.domain.DomainMetadata;
import br.net.ubre.io.txt.Line;
import br.net.ubre.io.txt.Text;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 02/04/2015
 * 
 */
public class DomainMetaConverter extends LineConverter {

	@Override
	public List<DomainMetadata> asObject(Context context, Line line) {
		Map<String, String> map = getPipeMap(line.getValues());
		List<DomainMetadata> metas = new ArrayList<DomainMetadata>();
		String typeStr;
		String sizeStr;
		int size;
		DomainMetadata meta;
		ValueType type;
		for (String name : map.keySet()) {
			typeStr = map.get(name);
			sizeStr = null;
			size = -1;
			if (typeStr.indexOf('(') > 0) {
				sizeStr = Text.block(typeStr, '(', ')');
				typeStr = Text.bc(typeStr, '(');
				size = Integer.parseInt(sizeStr);
			}
			type = ValueType.get(typeStr);
			meta = new DomainMetadata(name, type, size);
			metas.add(meta);
		}
		return metas;
	}

	@Override
	public Line asLine(ContextElement parent, Object object) {
		List<DomainMetadata> list;
		return null;
	}

}

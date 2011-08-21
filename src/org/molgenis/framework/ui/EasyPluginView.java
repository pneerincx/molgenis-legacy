package org.molgenis.framework.ui;

import org.molgenis.framework.ui.html.HtmlElement;

public abstract class EasyPluginView<M extends EasyPluginModel> extends SimpleScreenView<M>
{
	private static final long serialVersionUID = 1L;
	
	public EasyPluginView(M model)
	{
		super(model);
	}
	
	public abstract HtmlElement getInputs(M model);

	@Override
	public String render()
	{
		return getInputs(getModel()).render();
	}
}

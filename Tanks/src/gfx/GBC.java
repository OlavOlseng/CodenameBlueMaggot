package gfx;

import java.awt.GridBagConstraints;

public class GBC extends GridBagConstraints {
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GBC setSpan(int gridwidth, int gridheight) {
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		return this;
	}

	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	public GBC setInsets(int distance) {
		this.insets = new java.awt.Insets(distance, distance, distance, distance);
		return this;
	}

	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new java.awt.Insets(top, left, bottom, right);
		return this;
	}

	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}

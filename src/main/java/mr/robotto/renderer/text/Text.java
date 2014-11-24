/*
 * MrRobotto Engine
 * Copyright (c) 2014, Aarón Negrín, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package mr.robotto.renderer.text;

import mr.robotto.renderer.primitives.Sprite;

public class Text extends Sprite{
	
	/*private final static int TEXT_SIZE = 16;
	private static int bitmapWidth = 128;
	private static int bitmapHeight = 32;

	public Text(String str) {
		super(genTexture(str));
	}
	
	public void setText(String str) {
		Texture tex = genTexture(str);
		//tex.initialize();
		this.setTexture(tex);
	}
	
	private static Texture genTexture(String str) {
		Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		bitmap.eraseColor(0);
		Paint fillPaint = new Paint();
		fillPaint.setColor(WHITE);
		fillPaint.setAntiAlias(true);
		fillPaint.setTextSize(TEXT_SIZE);
		fillPaint.setStyle(Style.FILL);
		canvas.drawText(str, 20, TEXT_SIZE, fillPaint);
		
		return new Texture(bitmap);
	}*/
}

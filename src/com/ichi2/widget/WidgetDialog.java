/***************************************************************************************
 * Copyright (c) 2009 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 * Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *                                                                                      *
 * This program is free software; you can redistribute it and/or modify it under        *
 * the terms of the GNU General Public License as published by the Free Software        *
 * Foundation; either version 3 of the License, or (at your option) any later           *
 * version.                                                                             *
 *                                                                                      *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                      *
 * You should have received a copy of the GNU General Public License along with         *
 * this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 ****************************************************************************************/

package com.ichi2.widget;

import com.ichi2.anki.AnkiDroidWidgetBig;
import com.ichi2.anki.DeckManager;
import com.ichi2.themes.Themes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;

public class WidgetDialog extends Activity {

	public static final String ACTION_SHOW_DECK_SELECTION_DIALOG = "org.ichi2.WidgetDialog.SHOWDECKSELECTIONDIALOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Intent intent = getIntent();
    	if (intent != null) {
    		String action = intent.getAction();
    		if (action != null) {
    			if (ACTION_SHOW_DECK_SELECTION_DIALOG.equals(action)) {
    				DeckManager.getSelectDeckDialog(this, new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
		            		Intent newIntent = new Intent(WidgetDialog.this, AnkiDroidWidgetBig.UpdateService.class);
		            		newIntent.setAction(AnkiDroidWidgetBig.UpdateService.ACTION_OPENDECK);
		            		newIntent.putExtra(AnkiDroidWidgetBig.UpdateService.EXTRA_DECK_PATH, DeckManager.getDeckPath(which));
		            		startService(newIntent);
						}
    				}, null, new OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface arg0) {
		            		WidgetDialog.this.finish();
						}
    					
    				}).show();
    			}
    		}
    	}
    }
}

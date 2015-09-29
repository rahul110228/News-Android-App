/**
* Android ownCloud News
*
* @author David Luhmer
* @copyright 2013 David Luhmer david-dev@live.de
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU AFFERO GENERAL PUBLIC LICENSE
* License as published by the Free Software Foundation; either
* version 3 of the License, or any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU AFFERO GENERAL PUBLIC LICENSE for more details.
*
* You should have received a copy of the GNU Affero General Public
* License along with this library.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package de.luhmer.owncloudnewsreader.reader.owncloud;

import android.content.Context;

import de.luhmer.owncloudnewsreader.reader.FeedItemTags;
import de.luhmer.owncloudnewsreader.reader.OnAsyncTaskCompletedListener;

public class OwnCloud_Reader {
	boolean isSyncRunning = false;
	private API api = null;

	public OwnCloud_Reader() {
	}
	
	public void Start_AsyncTask_GetItems(Context context, OnAsyncTaskCompletedListener listener, FeedItemTags tag) {
		setSyncRunning(true);
		new AsyncTask_GetItems(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }, api).execute(tag);
	}

	public void Start_AsyncTask_GetOldItems(Context context, OnAsyncTaskCompletedListener listener, Long feed_id, Long folder_id) {
		setSyncRunning(true);
		new AsyncTask_GetOldItems(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }, feed_id, folder_id, api).execute();
	}
	
	public void Start_AsyncTask_GetFolder(Context context, OnAsyncTaskCompletedListener listener) {
		setSyncRunning(true);
		new AsyncTask_GetFolderTags(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }, api).execute();
	}
	
	public void Start_AsyncTask_GetFeeds(Context context, OnAsyncTaskCompletedListener listener) {
		setSyncRunning(true);
		new AsyncTask_GetFeeds(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }, api).execute();
	}

	public void Start_AsyncTask_PerformItemStateChange(Context context, OnAsyncTaskCompletedListener listener) {
		setSyncRunning(true);
		new AsyncTask_PerformItemStateChange(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }, api).execute();
	}
	
	
	public void Start_AsyncTask_GetVersion(Context context, OnAsyncTaskCompletedListener listener) {
		setSyncRunning(true);
		new AsyncTask_GetApiVersion(context, new OnAsyncTaskCompletedListener[] { AsyncTask_finished, listener }).execute();
	}

	public boolean isSyncRunning() {
		return isSyncRunning;
	}
	
	public void setSyncRunning(boolean isSyncRunning) {
		this.isSyncRunning = isSyncRunning;
	}

	OnAsyncTaskCompletedListener AsyncTask_finished = new OnAsyncTaskCompletedListener() {
		
		@Override
		public void onAsyncTaskCompleted(int task_id, Object task_result) {
			setSyncRunning(false);
		}
	};

	public API getApi() {
		return api;
	}

	public void setApi(API api) {
		this.api = api;
	}	
}

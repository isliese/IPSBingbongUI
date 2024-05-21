import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.viewModelScope;

import com.example.ipsbingbongui.AppDatabase;
import com.example.ipsbingbongui.ImageDao;
import com.example.ipsbingbongui.ImageEntity;

import java.util.List;
import kotlinx.coroutines.launch;

public class ImageViewModel extends AndroidViewModel {

    private ImageDao imageDao;
    private LiveData<List<ImageEntity>> allImages;

    public ImageViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        imageDao = db.imageDao();
        allImages = imageDao.getAllImages();
    }

    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    public void insert(ImageEntity image) {
        viewModelScope.launch {
            imageDao.insert(image);
        }
    }
}
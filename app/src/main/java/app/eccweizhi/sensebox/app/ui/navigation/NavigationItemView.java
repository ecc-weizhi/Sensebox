package app.eccweizhi.sensebox.app.ui.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import app.eccweizhi.sensebox.app.R;

public class NavigationItemView extends FrameLayout {
    private static final int RES_ID_BACKGROUND_SELECTED = R.drawable.rounded_rectangle_accent_bg;
    private static final int RES_ID_BACKGROUND_UNSELECTED = R.drawable.rounded_rectangle_transparent_bg;
    private static final int RES_ID_TEXT_COLOR_UNSELECTED = R.color.appcore_black_87;

    private ViewGroup container;
    private ImageView imageView;
    private TextView textView;

    // stuff to save
    @DrawableRes
    private int selectedIconResId;
    @DrawableRes
    private int unselectedIconResId;
    private boolean isNavStateSelected;

    @ColorInt
    private int accentColorInt;

    public NavigationItemView(@NonNull Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public NavigationItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public NavigationItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public NavigationItemView(@NonNull Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(@NonNull Context context,
                      @Nullable AttributeSet attrs,
                      int defStyleAttr,
                      int defStyleRes) {
        LayoutInflater.from(context).inflate(R.layout.layout_navigation_item, this, true);

        container = findViewById(R.id.navContainer);
        imageView = findViewById(R.id.navImageView);
        textView = findViewById(R.id.navTextView);


        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        accentColorInt = a.getColor(0, 0);
        a.recycle();

        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.NavigationItemView, defStyleAttr, defStyleRes);
        try {
            setIcon(typedArray.getResourceId(R.styleable.NavigationItemView_navSelectedIconSrc, 0),
                    typedArray.getResourceId(R.styleable.NavigationItemView_navUnselectedIconSrc, 0));

            setText(typedArray.getString(R.styleable.NavigationItemView_navText));

            setNavStateSelected(typedArray.getBoolean(R.styleable.NavigationItemView_navStateSelected, false));
        } finally {
            typedArray.recycle();
        }

        handleBackground();
        handleTextColor();
    }

    public void setText(@StringRes int textRes) {
        textView.setText(textRes);
    }

    public void setText(@Nullable CharSequence text) {
        textView.setText(text);
    }

    public void setIcon(@DrawableRes int selectedIconResId,
                        @DrawableRes int unselectedIconResId) {
        this.selectedIconResId = selectedIconResId;
        this.unselectedIconResId = unselectedIconResId;

        handleIcon();
    }

    private void handleIcon() {
        Drawable icon;
        if (isNavStateSelected) {
            icon = ContextCompat.getDrawable(getContext(), selectedIconResId);
        } else {
            icon = ContextCompat.getDrawable(getContext(), unselectedIconResId);
        }
        imageView.setImageDrawable(icon);
    }

    private void handleBackground() {
        if (isNavStateSelected) {
            container.setBackgroundResource(RES_ID_BACKGROUND_SELECTED);
        } else {
            container.setBackgroundResource(RES_ID_BACKGROUND_UNSELECTED);
        }
    }

    private void handleTextColor() {
        if (isNavStateSelected) {
            textView.setTextColor(accentColorInt);
        } else {
            textView.setTextColor(ContextCompat.getColor(getContext(), RES_ID_TEXT_COLOR_UNSELECTED));
        }
    }

    public void setNavStateSelected(boolean isSelected) {
        isNavStateSelected = isSelected;
        handleIcon();
        handleTextColor();
        handleBackground();
    }

    public boolean getNavStateSelected() {
        return isNavStateSelected;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        //begin boilerplate code that allows parent classes to save state
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        //end

        ss.selectedIconResId = selectedIconResId;
        ss.unselectedIconResId = unselectedIconResId;
        ss.isNavStateSelected = isNavStateSelected;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        //begin boilerplate code so parent classes can restore state
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        //end

        setIcon(ss.selectedIconResId, ss.unselectedIconResId);
        setNavStateSelected(ss.isNavStateSelected);
    }


    private static class SavedState extends BaseSavedState {
        @DrawableRes
        private int selectedIconResId;
        @DrawableRes
        private int unselectedIconResId;
        private boolean isNavStateSelected;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            selectedIconResId = in.readInt();
            unselectedIconResId = in.readInt();
            isNavStateSelected = in.readInt() == 1;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            out.writeInt(selectedIconResId);
            out.writeInt(unselectedIconResId);
            out.writeInt(isNavStateSelected ? 1 : 0);
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}

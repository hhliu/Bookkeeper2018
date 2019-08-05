package cycu.nclab.moocs.bookkeeper2018.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "moneycare")
public class MoneyEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String category;
    private String item;
    @ColumnInfo(name = "money")
    @NonNull
    private double price;
    @NonNull
    @ColumnInfo(name = "myDate")
    private String timestamp;
    private String payStyle;
    private String memo;

    private byte[] thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(String payStyle) {
        this.payStyle = payStyle;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }


    public boolean equals(Object arg) {
        if ((arg != null) && (arg instanceof MoneyEntity)) {
            if (this.price == ((MoneyEntity) arg).getPrice())
                if (this.category.equals(((MoneyEntity) arg).getCategory()))
                    if (this.item.equals(((MoneyEntity) arg).getItem()))
                        if (this.timestamp.equals(((MoneyEntity) arg).getTimestamp()))
                            if (this.memo.equals(((MoneyEntity) arg).getMemo()))
                                return true;
        }
        return false;
    }
}

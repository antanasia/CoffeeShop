package com.example.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.coffeeshop.Prevalent.Prevalent;

import io.paperdb.Paper;
import xyz.hanks.library.bang.SmallBangView;

public class Menu extends AppCompatActivity {

    SmallBangView likeItem1, likeItem2, likeItem3, likeItem4;
    ImageView imageItem1, imageItem2, imageItem3, imageItem4;
    private ImageView profile, listBtn;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        likeItem1 = findViewById(R.id.heart1);
        likeItem2 = findViewById(R.id.heart2);
        likeItem3 = findViewById(R.id.heart3);
        likeItem4 = findViewById(R.id.heart4);

        imageItem1 = findViewById(R.id.coffee1);
        imageItem2 = findViewById(R.id.coffee2);
        imageItem3 = findViewById(R.id.coffee3);
        imageItem4 = findViewById(R.id.coffee4);

        likeItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeItem1.isSelected()) {
                    likeItem1.setSelected(false);
                } else {
                    likeItem1.setSelected(true);
                    likeItem1.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                }
            }
        });

        likeItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeItem2.isSelected()) {
                    likeItem2.setSelected(false);
                } else {
                    likeItem2.setSelected(true);
                    likeItem2.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                }
            }
        });

        likeItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeItem3.isSelected()) {
                    likeItem3.setSelected(false);
                } else {
                    likeItem3.setSelected(true);
                    likeItem3.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                }
            }
        });

        likeItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeItem4.isSelected()) {
                    likeItem4.setSelected(false);
                } else {
                    likeItem4.setSelected(true);
                    likeItem4.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                }
            }
        });

        imageItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                screenDetail(id);
            }
        });

        imageItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 1;
                screenDetail(id);
            }
        });

        imageItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 2;
                screenDetail(id);
            }
        });

        imageItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 3;
                screenDetail(id);
            }
        });

        profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent signoutIntnent = new Intent(Menu.this, Profile.class);
                startActivity(signoutIntnent);
            }
        });

        listBtn = findViewById(R.id.list);

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(Menu.this, CustomerList.class);
                startActivity(listIntent);
            }
        });
    }

    public void screenDetail(int id) {
        Intent detail = new Intent(this, Detail.class);
        detail.putExtra("id", id);
        startActivity(detail);
    }

}
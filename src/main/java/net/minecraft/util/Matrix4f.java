package net.minecraft.util;

import fun.spmc.utils.math.Quaternion;

public class Matrix4f extends org.lwjgl.util.vector.Matrix4f {
    public Matrix4f(float[] p_i46413_1_) {
        this.m00 = p_i46413_1_[0];
        this.m01 = p_i46413_1_[1];
        this.m02 = p_i46413_1_[2];
        this.m03 = p_i46413_1_[3];
        this.m10 = p_i46413_1_[4];
        this.m11 = p_i46413_1_[5];
        this.m12 = p_i46413_1_[6];
        this.m13 = p_i46413_1_[7];
        this.m20 = p_i46413_1_[8];
        this.m21 = p_i46413_1_[9];
        this.m22 = p_i46413_1_[10];
        this.m23 = p_i46413_1_[11];
        this.m30 = p_i46413_1_[12];
        this.m31 = p_i46413_1_[13];
        this.m32 = p_i46413_1_[14];
        this.m33 = p_i46413_1_[15];
    }

    public Matrix4f() {
        this.m00 = this.m01 = this.m02 = this.m03 = this.m10 = this.m11 = this.m12 = this.m13 = this.m20 = this.m21 = this.m22 = this.m23 = this.m30 = this.m31 = this.m32 = this.m33 = 0.0F;
    }

    public Matrix4f(Quaternion quaternion) {
        float f = quaternion.i();
        float g = quaternion.j();
        float h = quaternion.k();
        float i = quaternion.r();
        float j = 2.0F * f * f;
        float k = 2.0F * g * g;
        float l = 2.0F * h * h;
        this.m00 = 1.0F - k - l;
        this.m11 = 1.0F - l - j;
        this.m22 = 1.0F - j - k;
        this.m33 = 1.0F;
        float m = f * g;
        float n = g * h;
        float o = h * f;
        float p = f * i;
        float q = g * i;
        float r = h * i;
        this.m10 = 2.0F * (m + r);
        this.m01 = 2.0F * (m - r);
        this.m20 = 2.0F * (o - q);
        this.m02 = 2.0F * (o + q);
        this.m21 = 2.0F * (n + p);
        this.m12 = 2.0F * (n - p);
    }

    public Matrix4f(Matrix4f matrix4f) {
        this.m00 = matrix4f.m00;
        this.m01 = matrix4f.m01;
        this.m02 = matrix4f.m02;
        this.m03 = matrix4f.m03;
        this.m10 = matrix4f.m10;
        this.m11 = matrix4f.m11;
        this.m12 = matrix4f.m12;
        this.m13 = matrix4f.m13;
        this.m20 = matrix4f.m20;
        this.m21 = matrix4f.m21;
        this.m22 = matrix4f.m22;
        this.m23 = matrix4f.m23;
        this.m30 = matrix4f.m30;
        this.m31 = matrix4f.m31;
        this.m32 = matrix4f.m32;
        this.m33 = matrix4f.m33;
    }

    public void multiplyWithTranslation(float f, float g, float h) {
        this.m03 = this.m00 * f + this.m01 * g + this.m02 * h + this.m03;
        this.m13 = this.m10 * f + this.m11 * g + this.m12 * h + this.m13;
        this.m23 = this.m20 * f + this.m21 * g + this.m22 * h + this.m23;
        this.m33 = this.m30 * f + this.m31 * g + this.m32 * h + this.m33;
    }

    public static Matrix4f createScaleMatrix(float f, float g, float h) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.m00 = f;
        matrix4f.m11 = g;
        matrix4f.m22 = h;
        matrix4f.m33 = 1.0F;
        return matrix4f;
    }

    public static Matrix4f createTranslateMatrix(float f, float g, float h) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.m00 = 1.0F;
        matrix4f.m11 = 1.0F;
        matrix4f.m22 = 1.0F;
        matrix4f.m33 = 1.0F;
        matrix4f.m03 = f;
        matrix4f.m13 = g;
        matrix4f.m23 = h;
        return matrix4f;
    }

    public void multiply(Matrix4f matrix4f) {
        float f = this.m00 * matrix4f.m00 + this.m01 * matrix4f.m10 + this.m02 * matrix4f.m20 + this.m03 * matrix4f.m30;
        float g = this.m00 * matrix4f.m01 + this.m01 * matrix4f.m11 + this.m02 * matrix4f.m21 + this.m03 * matrix4f.m31;
        float h = this.m00 * matrix4f.m02 + this.m01 * matrix4f.m12 + this.m02 * matrix4f.m22 + this.m03 * matrix4f.m32;
        float i = this.m00 * matrix4f.m03 + this.m01 * matrix4f.m13 + this.m02 * matrix4f.m23 + this.m03 * matrix4f.m33;
        float j = this.m10 * matrix4f.m00 + this.m11 * matrix4f.m10 + this.m12 * matrix4f.m20 + this.m13 * matrix4f.m30;
        float k = this.m10 * matrix4f.m01 + this.m11 * matrix4f.m11 + this.m12 * matrix4f.m21 + this.m13 * matrix4f.m31;
        float l = this.m10 * matrix4f.m02 + this.m11 * matrix4f.m12 + this.m12 * matrix4f.m22 + this.m13 * matrix4f.m32;
        float m = this.m10 * matrix4f.m03 + this.m11 * matrix4f.m13 + this.m12 * matrix4f.m23 + this.m13 * matrix4f.m33;
        float n = this.m20 * matrix4f.m00 + this.m21 * matrix4f.m10 + this.m22 * matrix4f.m20 + this.m23 * matrix4f.m30;
        float o = this.m20 * matrix4f.m01 + this.m21 * matrix4f.m11 + this.m22 * matrix4f.m21 + this.m23 * matrix4f.m31;
        float p = this.m20 * matrix4f.m02 + this.m21 * matrix4f.m12 + this.m22 * matrix4f.m22 + this.m23 * matrix4f.m32;
        float q = this.m20 * matrix4f.m03 + this.m21 * matrix4f.m13 + this.m22 * matrix4f.m23 + this.m23 * matrix4f.m33;
        float r = this.m30 * matrix4f.m00 + this.m31 * matrix4f.m10 + this.m32 * matrix4f.m20 + this.m33 * matrix4f.m30;
        float s = this.m30 * matrix4f.m01 + this.m31 * matrix4f.m11 + this.m32 * matrix4f.m21 + this.m33 * matrix4f.m31;
        float t = this.m30 * matrix4f.m02 + this.m31 * matrix4f.m12 + this.m32 * matrix4f.m22 + this.m33 * matrix4f.m32;
        float u = this.m30 * matrix4f.m03 + this.m31 * matrix4f.m13 + this.m32 * matrix4f.m23 + this.m33 * matrix4f.m33;
        this.m00 = f;
        this.m01 = g;
        this.m02 = h;
        this.m03 = i;
        this.m10 = j;
        this.m11 = k;
        this.m12 = l;
        this.m13 = m;
        this.m20 = n;
        this.m21 = o;
        this.m22 = p;
        this.m23 = q;
        this.m30 = r;
        this.m31 = s;
        this.m32 = t;
        this.m33 = u;
    }

    public void multiply(Quaternion quaternion) {
        multiply(new Matrix4f(quaternion));
    }

    public void multiply(float f) {
        this.m00 *= f;
        this.m01 *= f;
        this.m02 *= f;
        this.m03 *= f;
        this.m10 *= f;
        this.m11 *= f;
        this.m12 *= f;
        this.m13 *= f;
        this.m20 *= f;
        this.m21 *= f;
        this.m22 *= f;
        this.m23 *= f;
        this.m30 *= f;
        this.m31 *= f;
        this.m32 *= f;
        this.m33 *= f;
    }

    public Matrix4f copy() {
        return new Matrix4f(this);
    }
}

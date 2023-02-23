/**
 *
 * 内联函数
 *inline 使用 inline 修饰类已经被弃用了。
 *
 * The inline modifier for inline classes is deprecated!
 */
@JvmInline
value class Password(val value: String)

fun main() {
    // 不存在 'Password' 类的真实实例对象
    // 在运行时，'securePassword' 仅仅包含 'String'
    val securePassword = Password("Don't try this in production")

    println("ddddd")
    //println(-score!)
}
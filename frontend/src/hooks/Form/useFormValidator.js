function validatePassSame(form) {
  return (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请再次输入密码'));
    } else if (value !== form.password) {
      callback(new Error('两次输入密码不一致!'));
    } else {
      callback();
    }
  }
}

export default function useFormValidator(form, type) {
  if (type === 'PasswordSame') return validatePassSame(form)
  else {
    console.log("unknown validator( type")
  }
}

const colors = [
  '#2196F3',
  '#32c787',
  '#00BCD4',
  '#ff5652',
  '#ffc107',
  '#ff85af',
  '#FF9800',
  '#39bbb0',
  '#f56a00',
  '#7265e6',
  '#ffbf00',
  '#00a2ae',
]

/**
 * 根据字符串返回随机的颜色
 * @param {String}} str
 */
export function getStrColor(str) {
  let hash = 0
  for (let i = 0; i < str.length; i++) {
    hash = 31 * hash + str.charCodeAt(i)
  }
  return colors[Math.abs(hash % colors.length)]
}

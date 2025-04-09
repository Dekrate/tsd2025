module FactorialModule
  def factorial
    raise "Runtime error: cannot count factorial for negative number" if self < 0
    return 1 if self == 0
    (1..self).inject(1) { |product, n| product * n }
  end
end

class Integer
  
  include FactorialModule
  module InstanceModule
    def square
      self * self
    end
  end

  include InstanceModule


  module ClassModule
    def sample(n)
      raise ArgumentError, "the number must be positive" if n < 0
      Array.new(n) { rand(0..20) }
    end

    alias random sample
  end

  extend ClassModule
end


puts "5.factorial  => #{5.factorial}"   
begin
  puts "-1.factorial => #{-1.factorial}"
rescue => e
  puts "-1.factorial => Error: #{e.message}" 
end

puts "5.square     => #{5.square}"      


puts "Integer.sample(3)  => #{Integer.sample(3).inspect}"  
begin
  puts "Integer.sample(-1) => #{Integer.sample(-1).inspect}"
rescue => e
  puts "Integer.sample(-1) => Error: #{e.message}"  
end


puts "Integer.random(3)  => #{Integer.random(3).inspect}"  
begin
  puts "Integer.random(-1) => #{Integer.random(-1).inspect}"
rescue => e
  puts "Integer.random(-1) => Error: #{e.message}"
end

def min_max(arr)
  raise ArgumentError, "Array must contain exactly 5 elements" if arr.size != 5
  arr.each { |n| raise ArgumentError, "All numbers must be positive" if n <= 0 }

  total_sum = arr.sum
  min_sum = total_sum - arr.max
  max_sum = total_sum - arr.min
  "#{min_sum} #{max_sum}"
end

puts min_max([1, 2, 3, 4, 5])   
puts min_max([2, 8, 3, 5, 1])  

def decimal(binary_str)
  
  unless binary_str.match?(/\A[01]+\z/)
    raise ArgumentError, "this is not a binary number"
  end

  
  decimal_value = 0
  
  binary_str.each_char.with_index do |char, index|
    power = binary_str.size - index - 1
    decimal_value += char.to_i * (2 ** power)
  end
  decimal_value
end


puts decimal("101")  

begin
  puts decimal("231")
rescue ArgumentError => e
  puts e.message     
end

def pangram?(sentence)
  letters = sentence.downcase.scan(/[a-z]/).uniq
  letters.size == 26
end


puts pangram?("The quick brown fox jumps over the lazy dog.")
puts pangram?("abde")                                        

class Clock
  include Comparable  

  attr_reader :hours, :minutes, :seconds

  def initialize(hours, minutes, seconds)
    total = hours * 3600 + minutes * 60 + seconds
    total %= 24 * 3600 
    
    @hours = total / 3600
    @minutes = (total % 3600) / 60
    @seconds = total % 60
  end


  def total_seconds
    @hours * 3600 + @minutes * 60 + @seconds
  end

 
  def add_time(sec)
    total = total_seconds + sec
    total %= 24 * 3600  
    
    @hours = total / 3600
    @minutes = (total % 3600) / 60
    @seconds = total % 60
  end

  
  def +(sec)
    new_clock = Clock.new(@hours, @minutes, @seconds)
    new_clock.add_time(sec)
    new_clock
  end

  
  def -(sec)
    self + (-sec)
  end

  
  def <=>(other)
    self.total_seconds <=> other.total_seconds
  end

  
  def ==(other)
    self.total_seconds == other.total_seconds
  end

  
  def print
    formatted_time = format("%02d:%02d:%02d", @hours, @minutes, @seconds)
    puts "The current time is #{formatted_time}"
  end

  
  def self.measure_time
    start_time = Time.now
    yield if block_given?
    end_time = Time.now
    elapsed = (end_time - start_time).round 
    puts "#{elapsed} seconds elapsed"
  end
end




clock = Clock.new(10, 0, 0)
clock.print  


clock = clock + 30
clock.print 


puts clock == Clock.new(10, 0, 30)


Clock.measure_time do
  puts "Something is happening here"
  sleep 3
end

